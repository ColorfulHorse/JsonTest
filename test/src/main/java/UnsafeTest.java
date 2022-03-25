import com.google.gson.internal.UnsafeAllocator;

/**
 * Author : greensunliao
 * Date   : 2022/3/25
 * Email  : liao962381394@sina.cn
 * Blog   : https://juejin.cn/user/3263006244363095
 * Desc   :
 */
public class UnsafeTest {

    static class UnsafePerson {
        String name = "name";

        UnsafePerson() {
            System.out.println("invoke UnsafePerson Constructor");
        }
    }

    public static void main(String[] args) {
        UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
        try {
            UnsafePerson person = unsafeAllocator.newInstance(UnsafePerson.class);
            System.out.println(person.name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
