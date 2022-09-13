package alarm;

public class CarAlarmSystem implements ICarAlarmSystem{
    private int tock = 0;
    private boolean isOpen = false;
    private boolean isLocked = false;
    private boolean isArmed = false;
    private boolean isFlashing = false;
    private boolean isScreaming = false;

    @Override
    public boolean opened() {
        return isOpen;
    }

    @Override
    public boolean closed() {
        return !isOpen;
    }

    @Override
    public boolean locked() {
        return isLocked;
    }

    @Override
    public boolean unlocked() {
        return !isLocked;
    }

    @Override
    public boolean flash() {
        return isFlashing;
    }

    @Override
    public boolean sound() {
        return isScreaming;
    }

    @Override
    public boolean armed() {
        return isArmed;
    }

    @Override
    public void lock() {
        isLocked = true;
        if (closed())
            tock = 0;
    }

    @Override
    public void unlock() {
        isLocked = false;
        isArmed = false;
        isFlashing = false;
        isScreaming = false;
    }

    @Override
    public void close() {
        isOpen = false;
        if (locked())
            tock = 0;
    }

    @Override
    public void open() {
        isOpen = true;
        if (armed()){
            isScreaming = true;
            isFlashing = true;
            tock = 0;
        }
    }

    @Override
    public void tick() {
        tock++;

        if (!armed()){
            if(locked() && closed() && tock >= 2){
                isArmed = true;
            }
        } else {
            if (isFlashing && tock > 30){
                isFlashing = false;
            }
            if (isScreaming && tock > 3){
                isScreaming = false;
            }
        }

    }

}
