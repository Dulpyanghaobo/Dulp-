package utils;
import java.util.UUID;
/*
 * ��������ַ���������UUID
 * @author Dulp
 * */
public class UUIDUtils {
public static String getUUID() {
	//��������ַ�������
	return UUID.randomUUID().toString().replace("-", "");
}
}
