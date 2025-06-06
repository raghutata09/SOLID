public class FlatFeeStratergy implements FeeStratergy{
    @Override
    public int calculateFee(Ticket ticket) {
        return (ticket.leaveTime - ticket.arrivalTime)*20;
    }
}
