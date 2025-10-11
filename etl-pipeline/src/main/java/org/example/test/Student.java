package org.example.test;

public class Student {

    String name;
    int id;

    Student(String name, int id){
        this.name=name;
        this.id=id;
    }

}
class abc{
    public static void main(String[] args){


        Student m1=new Student("malshan",9);
        Student m2=new Student("malshan",9);


        System.out.println(m1.name.equals(m2.name));

        String str = "apple;banana,orange|grape";
        String[] fruits = str.split("[,;|]");
        for(String fruit : fruits) {
            System.out.println(fruit);
        }


    }
}
