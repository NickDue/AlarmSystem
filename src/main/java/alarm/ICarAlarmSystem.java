package alarm;

public interface ICarAlarmSystem {
    boolean opened();
    boolean closed();
    boolean locked();
    boolean unlocked();
    boolean flash();
    boolean sound();
    boolean armed();

    void lock();
    void unlock();
    void close();
    void open();
    void tick();
}
