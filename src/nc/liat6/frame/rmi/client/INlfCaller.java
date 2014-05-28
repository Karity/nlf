package nc.liat6.frame.rmi.client;

import java.util.Map;

import nc.liat6.frame.json.element.IJsonElement;

/**
 * Զ�̵��ÿͻ��˽ӿ�
 * @author 6tail
 *
 */
public interface INlfCaller{
	
	/**
	 * ����
	 * @param ip ��������ַ
	 * @param port �˿�
	 * @param klass ������
	 * @param method ���÷���
	 * @param args ����
	 * @return ���ؽ��
	 */
	public IJsonElement call(String ip,int port,String klass,String method,Map<String,String> args);

}
