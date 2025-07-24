#include <stdio.h>

void main(){
    int a;
    int b;
    a=0;
    b=10;
   while( a < b){
        printf("while-loop::%d\n", a);
        a=a+1;
   }
   for(a=b-1;a>=0;a=a-1){
     printf("for---loop::%d\n",a);
   }
}