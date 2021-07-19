public class InvTransicion {

    private String name;
    private int[] grupos;


    public InvTransicion (String name, int[] grupos) {
        this.name = name;
        this.grupos = grupos;
    }

    public String getName () {
        return name;
    }

    public int[] getGrupos () {
        return grupos;
    }
    
}
