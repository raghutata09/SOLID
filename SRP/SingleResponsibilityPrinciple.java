package SRP;

// A class should have only 1 reason to change
public class SingleResponsibilityPrinciple {
    public static void main(String[] args) {

        /*
        Liskov PRinciple
        maanlo A ek class h which is implemented by B and C
        A a = new B();
        a.startEngine();

        Maanlo kuch function likha hua h

        ab isme change krdo B ki jgh C, and now maan lo C class m startEngine Error throw kr rha h
        to jab ham a.startEngine(); krenge to error throw krega yanki ki functionality break krega
        Liskov m isiliye ye h ki agar child class change krde to baki ka code narrow down ni hona chahie
        instead smootly chalna chahie
        A a = new C();
        a.startEngine();
         */


        /*
            Dependency Inversion


            KeyBoard -> WiredKeBoard, BluetoothKeyBoard
            Mouse -> WiredMouse,  BlueoothMouse

            This is wrong
            class Macbook{
                private final WiredKeBoard kb;
                private final BlueoothMouse mo;

                public Macbook(){
                    kb = new WiredKeBoard();
                    mo = new BlueoothMouse();
                }

            }

            This is write
            class Macbook{
                private final KeyBoard kb;
                private final Mouse mo;

                public Macbook(KeyBoard kb,Mouse mo ){
                    this.kb = kb;
                    this.mo = mo;
                }

            }
         */
    }

}
