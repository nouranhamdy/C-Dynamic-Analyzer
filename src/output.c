#include <stdio.h>
int visited [20] = {0} ;
int main ( ) {
visited [ 1 ] = 1 ; 
int i = 10 ; 
if ( i < 15 ) {
visited [ 2 ] = 1 ; 
if ( i > 20 ) {
visited [ 3 ] = 1 ; 
printf ( "i is smaller than 15\n" ) ; 
} 
if ( i < 9 ) {
visited [ 4 ] = 1 ; 
printf ( "i is smaller than 12 too\n" ) ; 
} 
else {
visited [ 5 ] = 1 ; 
printf ( "i is greater than 15" ) ; 
} 
} 
for ( int j = 0 ; 
j < 2 ; 
j ++ ) {
visited [ 6 ] = 1 ; 
i ++ ; 
}
for(int i=0 ; i< 20; i++){
 System.out.println(visited[i]);
} 
return 0 ; 
} 
 
