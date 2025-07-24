#include <stdio.h>

void main(){
    float a;
    int b;
    float c;
    b = 15;
    a = 4.15-(11.5*0.1);
    c = 0.05+(0.1/2.0);
    if(a>0.1){
        printf("a:%f b:%d\n", a, b);
    }
    else{
        printf("It will not happpen!");
    }
    if(a<c){
        printf("It will not happpen!");
    }
    else{
        printf("c:%f\n", c);
    }

    if(c==0.1){
        printf("Equal!!\n");
    }
    else{
        printf("Not Equal!!\n");
    }

    if(a!=c){
        printf("%f != %f \n", a,c);
    }
    else{
         printf("It will not happpen!");
    }

    if(a>c){
        printf("If then / If else \n& Float arithmetic computation \n& printf(flot and int)\n& Comparison(Float) expression\n");
    }
}