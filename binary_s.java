import java.io.*;
import java.util.*;

public class main{
    public static void main(String[]args) {
        
        Scanner sc= new Scanner(System.in);

        int n=sc.nextInt();//1

        int[] arr =new int[n];//2
        for(int i=0;i< n;i++){
            arr[i]= sc.nextInt();
        }

        int inpArrLen=sc.nextInt();//3

        int[] inputArr = new int[inpArrLen];//4
        for(int i=0;i<inpArrLen; i++){
            inputArr[i]=sc.nextInt();
        }

        PrintWriter pw =new PrintWriter(System.out);

        for(int el: inputArr){
            int b = binarySearch(arr,el);
            int l = lowerBoundIndex(arr,el);
            int r = upperBoundIndex(arr,el);

            pw.println(b+" "+l+" "+r+" ");
        }
        pw.close();
    }
     static int binarySearch(int[] arr, int el){
        int left=0;
        int right = arr.length-1;
        while(left<=right){
            int middle = left +((right - left) / 2);

            if (arr[middle] ==el){
                 return 1;
            } else if (arr[middle]< el){
                left = middle+1;
            } else {
                right = middle-1;
            }
        };
        return 0;
    }

     static int lowerBoundIndex(int[] arr,int el){
        int left=0;
        int right=arr.length;

        while(left<right){
            int middle=(left+right) /2;
            if (el<=arr[middle]){
                right=middle;
            }else{
                left=middle+1;
            }
        }
            return left;
    }

     static int upperBoundIndex(int[] arr,int x){
        int left=0;
        int right=arr.length;

        while(left<right){
            int middle=(right+left)/2;
            if (x<arr[middle]){
                right=middle;
            }else{
                left=middle+1;
            }
        }

            return left;
        }
}