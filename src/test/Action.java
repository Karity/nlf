package test;

import java.io.IOException;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.execute.upload.UploadedFile;
import nc.liat6.frame.paging.PageData;
import nc.liat6.frame.web.WebExecute;
import nc.liat6.frame.web.response.Alert;
import nc.liat6.frame.web.response.Json;
import nc.liat6.frame.web.response.Paging;
import nc.liat6.frame.web.upload.FileUploader;

/**
 * ʾ��
 * @author 6tail
 *
 */
public class Action{

  /**
   * �Զ���ҳ
   * 
   * @return
   */
  public Object paging(){
    Request r = Context.get(Statics.REQUEST);
    ITrans t = TransFactory.getTrans();
    PageData pd = t.getSelecter().table("TABLE_USER").page(r.getPageNumber(),r.getPageSize());
    t.rollback();
    t.close();
    Paging p = new Paging();
    p.setPageData(pd);
    p.setUri("/demo/paging.jsp");
    return p;
  }

  /**
   * �ļ��ϴ�
   * 
   * @return
   * @throws IOException
   */
  public Object upload(){
    Request r = Context.get(Statics.REQUEST);
    FileUploader uploader = r.find(WebExecute.TAG_UPLOADER);
    UploadedFile file = uploader.getFile();
    return new Json(file.getName());
  }
  
  public Object addData(){
    ITrans t = TransFactory.getTrans();
    for(int i=0;i<20;i++){
      t.getInserter().table("TABLE_USER").set("NAME","����"+i).set("AGE",1+(int)(Math.random()*100)).insert();
    }
    t.commit();
    t.close();
    return new Alert("���ݲ���ɹ���");
  }
}