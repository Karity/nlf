package nc.liat6.frame.web.upload;

import java.util.HashMap;
import java.util.Map;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.web.response.Json;
import nc.liat6.frame.web.upload.bean.UploadBean;

/**
 * �ļ��ϴ�״̬
 * @author 6tail
 *
 */
public class UploadStatus{
	
	/**�ļ�״̬��*/
	private static final Map<String,UploadBean> POOL = new HashMap<String,UploadBean>();
	
	/**
	 * ���һ���ϴ��ļ�
	 * @param id �ļ���ʶ
	 * @param ub �ļ�״̬��װ����
	 */
	public static void add(String id,UploadBean ub){
		POOL.put(id,ub);
	}
	
	/**
	 * �����ļ�״̬
	 * @param id �ļ���ʶ
	 * @param ub �ļ�״̬��װ����
	 */
	public static void update(String id,UploadBean ub){
		UploadBean o = POOL.get(id);
		if(null==o){
			add(id,ub);
		}else{
			o.setUploaded(ub.getUploaded());
			o.setTotal(ub.getTotal());
		}
	}
	
	/**
	 * ��ȡ�ļ�״̬
	 * @return
	 */
	public Object getStatus(){
		Request r = Context.get(Statics.REQUEST);
		String id = r.get(FileUploader.ARG_ID);
		UploadBean ub = POOL.get(id);
		if(null==ub){
			return new Json(new UploadBean());
		}
		return new Json(ub);
	}

}
