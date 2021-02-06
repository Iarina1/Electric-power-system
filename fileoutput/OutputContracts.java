package fileoutput;

public final class OutputContracts {
    private long consumerId;
    private long price;
    private long remainedContractMonths;


    public OutputContracts(final long consumerId,
                           final long price, final long remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(final long consumerId) {
        this.consumerId = consumerId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(final long price) {
        this.price = price;
    }

    public long getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final long remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}
