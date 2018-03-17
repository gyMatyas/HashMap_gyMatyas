public class MapMain {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        System.out.println(map.isEmpty());

        System.out.println(map.size());

        System.out.println(map.containsKey("béla"));

        System.out.println(map.containsValue("géza"));

        map.put("béla", "géza");
        //map.put("béla", "error"); // error
        map.put("hello", "hi");

        System.out.println(map.isEmpty());

        System.out.println(map.size());

        System.out.println(map.containsKey("béla"));

        System.out.println(map.containsValue("géza"));

        System.out.println(map.get("béla"));

        map.clearAll();

        // map.remove("béla");

        System.out.println(map.size());

        System.out.println(map.containsKey("béla"));

        //System.out.println(map.get("lul"));  // error
    }
}
