#include <stdio.h>

void main(){
    int a;
    int b;
    a=100-50+(10%2);
    b=a+((100/2)*4);
    
    if(a > b){
        printf("Hi a=%d\n", a);
    }
    else{
        printf("Hi b=%d\n", b);
    }
    
    if(a < b){
        printf("Hi a=%d\n", a);
    }
    else{
        printf("Hi b=%d\n", b);
    }

    if(a != b){
        printf("Not equal!\n");
    }
    else{
        printf("Equal!\n");
    }

    if(a == b){
        printf("Equal!\n");
    }
    else{
        printf("Not Equal!\n");
    }

    printf("If else \n& Integer arithmetic computation \n& printf\n& Comparison(Integer) expression\n");
    
}
