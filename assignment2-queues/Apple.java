/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Apple {
    private String name;

    public Apple(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Apple ap1 = new Apple("Jobs");
        Apple ap2 = new Apple("Kuli");
        Apple[] aplist = new Apple[];
        aplist[0] = ap1;
        aplist[1] = ap2;
        System.out.println(aplist[0].getName());
        System.out.println(aplist[1].getName());


    }
}
