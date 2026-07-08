package ar.edu.utn.ba.ddsi.climalert.utils;

public class IDGenerator
{
    private long next;

    public IDGenerator() {
        this.next = 0L;
    }
    public long next() {
        return this.next++;
    }
}
