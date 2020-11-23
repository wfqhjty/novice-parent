public class TeatMain {
    public static void main(String[] args) {
//        Integer integer1 = new Integer(7);
//        Integer integer2 = new Integer(7);
//        Integer integer3 = 7;
//        int i = 7;
//        System.out.println(integer1 == integer2);
//        System.out.println(integer1 == integer3);
//        System.out.println(integer1 == i);
        String str1 = new String("aaa");
        String str2 = new String("aaa");
        String str3 = "aaa";
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str1.equals(str2));
        System.out.println(str1.equals(str3));
    }
}
