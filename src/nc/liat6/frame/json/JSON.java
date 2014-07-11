package nc.liat6.frame.json;

import nc.liat6.frame.json.element.IJsonElement;
import nc.liat6.frame.json.parser.BeanParser;
import nc.liat6.frame.json.parser.JsonParser;
import nc.liat6.frame.json.wrapper.JsonWrapper;

/**
 * JSONת����
 * 
 * @author 6tail
 * 
 */
public class JSON{

  /** ������ģʽ */
  public static final String QUOTE_SINGLE = "'";
  /** ˫����ģʽ */
  public static final String QUOTE_MULTIPLE = "\"";
  /** Ĭ������ģʽ */
  public static final String QUOTE_DEFAULT = QUOTE_MULTIPLE;
  /** �����Ƿ�ʹ�����ŵ�Ĭ������ */
  public static final boolean DEFAULT_NUMBER_QUOTED = true;
  /** �����Ƿ�ʹ������ */
  public static final boolean NUMBER_QUOTED = DEFAULT_NUMBER_QUOTED;
  /** �Ƿ񼫼򣨲����������У���Ĭ������ */
  public static final boolean DEFAULT_TINY = true;
  /** �Ƿ񼫼򣨲����������У���ȫ������ */
  public static boolean TINY = DEFAULT_TINY;

  private JSON(){}

  /**
   * ������ת��ΪJSON�ַ���������ȫ�ּ�������
   * 
   * @param o ����
   * @return JSON�ַ���
   */
  public static String toJson(Object o){
    return toJson(o,TINY);
  }

  /**
   * ������ת��ΪJSON�ַ���
   * 
   * @param o ����
   * @param tiny �Ƿ��Ǽ��򣨲����������У�
   * @return JSON�ַ���
   */
  public static String toJson(Object o,boolean tiny){
    return toJson(o,tiny,QUOTE_DEFAULT);
  }

  /**
   * ������ת��ΪJSON�ַ���
   * 
   * @param o ����
   * @param tiny �Ƿ��Ǽ��򣨲����������У�
   * @param quote �ַ�����β����
   * @return JSON�ַ���
   */
  public static String toJson(Object o,boolean tiny,String quote){
    return toJson(o,tiny,QUOTE_DEFAULT,NUMBER_QUOTED);
  }

  /**
   * ������ת��ΪJSON�ַ���
   * 
   * @param o ����
   * @param tiny �Ƿ��Ǽ��򣨲����������У�
   * @param numberQuoted ���������Ƿ�ʹ������
   * @return JSON�ַ���
   */
  public static String toJson(Object o,boolean tiny,boolean numberQuoted){
    return toJson(o,tiny,QUOTE_DEFAULT,numberQuoted);
  }

  /**
   * ������ת��ΪJSON�ַ���
   * 
   * @param o ����
   * @param tiny �Ƿ��Ǽ��򣨲����������У�
   * @param quote �ַ�����β����
   * @param numberQuoted ���������Ƿ�ʹ������
   * @return JSON�ַ���
   */
  public static String toJson(Object o,boolean tiny,String quote,boolean numberQuoted){
    return new JsonWrapper(tiny,quote,numberQuoted).wrap(o);
  }

  /**
   * ��JSON�ַ���ת��Ϊͨ�÷�װ
   * 
   * @param s JSON�ַ���
   * @return ͨ�÷�װ
   */
  public static IJsonElement fromJson(String s){
    return new JsonParser().parse(s);
  }

  /**
   * ��JSON�ַ���ת��Ϊ����
   * 
   * @param s JSON�ַ���
   * @return ����
   */
  public static <T>T toBean(String s){
    return toBean(fromJson(s));
  }

  /**
   * ��ͨ�÷�װת��Ϊ����
   * 
   * @param je ͨ�÷�װ
   * @return ����
   */
  public static <T>T toBean(IJsonElement je){
    return new BeanParser().parse(je);
  }
}
