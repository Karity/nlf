package nc.liat6.frame.rmi.server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import nc.liat6.frame.Factory;
import nc.liat6.frame.Version;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.exception.NlfException;
import nc.liat6.frame.json.JSON;
import nc.liat6.frame.locale.L;
import nc.liat6.frame.locale.LocaleFactory;
import nc.liat6.frame.log.ILog;
import nc.liat6.frame.log.Logger;
import nc.liat6.frame.util.Stringer;

/**
 * Զ�̵��÷���
 * <p>
 * ��������˵��ã�NlfServer.getInstance().start();
 * </p>
 * 
 * @author 6tail
 * 
 */
public class NlfServer implements Runnable{

  /** �Ƿ���������־ */
  static boolean enableLog = true;
  /** �����ʶ */
  static final String REQ_TAG = "nlfreq";
  /** ��Ӧ��ʶ */
  static final String RSP_TAG = "nlfrsp";
  /** �����ļ����� */
  public static final String SETTING_FILE_NAME = "server";
  /** Ĭ�϶˿� */
  public static final int DEFAULT_PORT = 9999;
  /** Ĭ���̳߳ش�С */
  public static final int DEFAULT_POOL_SIZE = 100;
  /** Ĭ�ϻ�������С */
  public static final int DEFAULT_BUFFER_SIZE = 40960;
  /** �˿� */
  public static int PORT = DEFAULT_PORT;
  /** �̳߳ش�С */
  public static int POOL_SIZE = DEFAULT_POOL_SIZE;
  /** ��������С */
  public static int BUFFER_SIZE = DEFAULT_BUFFER_SIZE;
  /** ʵ�� */
  private static NlfServer instance = null;
  /** ��־ */
  private static ILog log = Logger.getLog();
  /** �̳߳� */
  private ExecutorService pool;
  /** ����socket */
  private ServerSocket serverSocket;
  /** �����߳� */
  private Thread t = null;
  /** ������ʵ�·�� */
  public static final List<String> allow = new ArrayList<String>();
  /** ��ֹ���ʵ�·�� */
  public static final List<String> forbid = new ArrayList<String>();

  private NlfServer(){
    try{
      serverSocket = new ServerSocket(PORT);
      serverSocket.setSoTimeout(0);
      pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
    }catch(IOException e){
      throw new NlfException(e);
    }
  }

  private static void init(){
    File f = new File(Factory.APP_PATH,SETTING_FILE_NAME);
    if(!f.exists()||f.isDirectory()){
      return;
    }
    Bean o;
    try{
      o = JSON.toBean(Stringer.readFromFile(f,"utf-8"));
    }catch(IOException e){
      throw new NlfException(e);
    }
    PORT = o.getInt("port",DEFAULT_PORT);
    POOL_SIZE = o.getInt("pool_size",DEFAULT_POOL_SIZE);
    BUFFER_SIZE = o.getInt("buffer_size",DEFAULT_BUFFER_SIZE);
    List<String> lallow = o.get("allow");
    if(null!=lallow){
      allow.addAll(lallow);
    }
    List<String> lforbid = o.get("forbid");
    if(null!=lforbid){
      forbid.addAll(lforbid);
    }
    forbid.add(Version.PACKAGE);
  }
  static{
    init();
  }

  public synchronized static NlfServer getInstance(){
    if(null==instance){
      instance = new NlfServer();
    }
    return instance;
  }

  public void start(){
    if(null==t){
      t = new Thread(this);
      t.start();
    }
    log.debug(L.get(LocaleFactory.locale,"rmi.app_start")+"\r\n\t"+L.get(LocaleFactory.locale,"rmi.port")+PORT+"\r\n\t"+L.get("rmi.threads")+POOL_SIZE+"\r\n\t"+L.get(LocaleFactory.locale,"rmi.buffer")+BUFFER_SIZE);
  }

  public void run(){
    while(null!=t&&t.isAlive()){
      Socket socket = null;
      try{
        socket = serverSocket.accept();
        if(enableLog){
          log.debug(Stringer.print("??:?",L.get(LocaleFactory.locale,"rmi.new_client"),socket.getInetAddress(),socket.getPort()));
        }
        pool.execute(new Handler(socket));
      }catch(Exception e){
        if(null!=socket){
          try{
            socket.close();
          }catch(IOException ex){}
        }
      }
      try{
        Thread.sleep(10);
      }catch(InterruptedException e){}
    }
  }

  public void nolog(){
    enableLog = false;
  }
}
