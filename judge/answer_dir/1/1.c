#include <stdio.h>
#include <stdlib.h>
int temp_1;

int partition(int* array,int low,int high)
{
    temp_1=array[low];
    while(low<high)
    {
        while(low<high&&array[high]>=temp_1)
            high--;
        if(low<high)
        {
            array[low]=array[high];
            low++;
        }
        while(low<high&&array[low]<temp_1)
            low++;
        if(low<high)
        {
            array[high]=array[low];
            high--;
        }
    }
    array[low]=temp_1;
    return low;
}
void quick_sort(int* array,int s,int t)
{
    int i=0;
    if(s<t)
    {
        i=partition(array,s,t);
        quick_sort(array,s,i-1);
        quick_sort(array,i+1,t);
    }
}
int maximumProduct(int* nums, int numsSize) {
    quick_sort(nums,0,numsSize-1);
    int result1=nums[0]*nums[1]*nums[numsSize-1];
    int result2=nums[numsSize-1]*nums[numsSize-2]*nums[numsSize-3];
    return result1>result2?result1:result2;
}

int main(int argc, char *argv[]) {
    int nums[10000] = {0};
    int numsSize = argc;
    for (int i = 0; i < numsSize; i++){
        nums[i] = atoi(argv[i]);
    }
    printf("%d\n", maximumProduct(nums, numsSize));
    return 0;
}