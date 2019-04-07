package com.xsp.gradle.todo;

@testAnnotation
public class App {

    public static void main(String[] args) {

        boolean hasAnnotation = App.class.isAnnotationPresent(testAnnotation.class);

        System.out.println(hasAnnotation+","+testAnnotation.class+","+App.class);
        if ( hasAnnotation ) {
            testAnnotation testAnnotation = App.class.getAnnotation(testAnnotation.class);

            System.out.println("id:"+testAnnotation.id());
            System.out.println("msg:"+testAnnotation.msg());
        }
    }

}
@interface testAnnotation{
    int id() default 1;
    String msg() default "I am test";
}
