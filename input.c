//#include<stdio.h>
int main() {

    int i = 10;

    if (i < 15)
    {
        if (i > 20) {            //this block must be red
           printf("i is smaller than 15\n");
        }
        if (i < 9)  {            //this block must be green
          printf("i is smaller than 12 too\n");
        }
        else {                 //this block must be red
            printf("i is greater than 15");
        }
    }

    for(int j=0; j<2 ; j++){
        i++;
    }

    return 0;
}