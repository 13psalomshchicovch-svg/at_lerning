package less9;

import java.util.Arrays;

public class LuckyTicketChecker {

    private int[] ticket = new int[8];

    public LuckyTicketChecker(int[] ticket){
        if (this.ticket.length != ticket.length){
            throw new IllegalArgumentException("error numbers!");
        }

        this.ticket = ticket;
    }

    private boolean checkThatEvenSumMoreThenOddSum(){
        int evenSum = 0;
        int oddSum = 0;

        for (int i = 0; i < ticket.length; i++ ){
            if (i % 2 == 0){
                oddSum += ticket[i];
            }
            else {
                evenSum += ticket[i];
            }
        }

        return evenSum - oddSum == 7;
    }

    private boolean multiplicationFirstThreeNumberOnLastThreeNumber(){
        if (ticket[7]*ticket[6]*ticket[5] == 0){
           return false;
        }
        else {

            return (ticket[0]*ticket[1]*ticket[2]) % (ticket[7]*ticket[6]*ticket[5]) == 0;
        }
    }

    private boolean findMatchBetweenNumbers(){
        int[] sortArrNb;
        int nextNumb;
        int count = 0;

        sortArrNb = Arrays.stream(ticket).sorted().toArray();

        for (int i = 0; i < sortArrNb.length-1;i++ ){
            nextNumb = sortArrNb[i+1];
            if (nextNumb == sortArrNb[i]){count++;}
        }

       return count == 1;
    }


    public boolean isLuckyNewGen() {
        boolean first, second, third;

            first = checkThatEvenSumMoreThenOddSum();
            second = multiplicationFirstThreeNumberOnLastThreeNumber();
            third = findMatchBetweenNumbers();

        return first && second && third;
    }

    static void main(String[] args) {

        int[] ticket = new int[]{0,0,1,2,3,5,4,8};

        LuckyTicketChecker luckyTicket = new LuckyTicketChecker(ticket);

        System.out.println(luckyTicket.isLuckyNewGen());

    }
    // можно добавить вспомогательные приватные методы
}
