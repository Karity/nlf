package nc.liat6.frame.web.upload.impl;

import nc.liat6.frame.execute.upload.IProgressListener;
import nc.liat6.frame.web.upload.UploadStatus;
import nc.liat6.frame.web.upload.bean.UploadBean;

/**
 * �ļ��ϴ�����
 * 
 * @author 6tail
 * 
 */
public class UploadListener implements IProgressListener{

  /** �ļ���ʶ */
  private String id;

  public UploadListener(String id){
    this.id = id;
    UploadBean ub = new UploadBean();
    ub.setId(id);
    UploadStatus.add(id,ub);
  }

  public void update(long read,long total){
    UploadBean ub = new UploadBean();
    ub.setId(id);
    ub.setTotal(total);
    ub.setUploaded(read);
    UploadStatus.update(id,ub);
  }
}
