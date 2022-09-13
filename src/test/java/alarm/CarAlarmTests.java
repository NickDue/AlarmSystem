package alarm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarAlarmTests{
    private ICarAlarmSystem system;

    @BeforeEach
    void init() {
        system = new CarAlarmSystem();
    }

    @Tag("Close Door")
    @Test
    public void CloseDoor(){
        boolean expected = true;

        system.close();
        boolean actual = system.closed();

        assertEquals(expected, actual);
    }

    @Tag("Open Door")
    @Test
    public void openDoor(){
        boolean expected = true;

        system.open();
        boolean actual = system.opened();

        assertEquals(expected, actual);
    }

    @Tag("Unlock Car")
    @Test
    public void unlockCar(){
        system.unlock();

        assertTrue(system.unlocked());
    }

    @Tag("Flash is off when unlocked")
    @Test
    public void unlockCarFlashOff(){
        system.unlock();
        assertFalse(system.flash());
    }

    @Tag("Sound is off when unlocked")
    @Test
    public void unlockCarSoundOff(){
        system.unlock();
        assertFalse(system.sound());
    }

    @Tag("Alarm is off when unlocked")
    @Test
    public void unlockCarAlarmOff(){
        system.unlock();
        assertFalse(system.armed());
    }

    @Tag("Lock Car")
    @Test
    public void lockCar(){
        boolean expected = true;

        system.lock();
        boolean actual = system.locked();

        assertEquals(expected, actual);
    }

    @Tag("Car is armed when 2 seconds has passed")
    @Test
    public void carIsArmed(){
        system.close();
        system.lock();

        system.tick();
        system.tick();
        assertTrue(system.armed());
    }

    @Tag("Car is not armed when 1 seconds has passed")
    @Test
    public void carIsNotArmed(){
        system.close();
        system.lock();

        system.tick();
        assertFalse(system.armed());
    }

    @Tag("Alarm sounds")
    @Test
    public void alarmSounds(){
        system.close();
        system.lock();
        system.tick();
        system.tick();

        system.open();
        assertTrue(system.sound());
    }

    @Tag("Alarm sounds for 3 seconds")
    @Test
    public void alarmSoundsThreeSeconds(){
        system.close();
        system.lock();
        system.tick();
        system.tick();

        system.open();

        system.tick();
        system.tick();
        system.tick();
        assertTrue(system.sound());
    }

    @Tag("Alarm sound stops after 3 seconds")
    @Test
    public void alarmSoundStops(){
        system.close();
        system.lock();
        system.tick();
        system.tick();

        system.open();

        system.tick();
        system.tick();
        system.tick();
        system.tick();
        assertFalse(system.sound());
    }

    @Tag("Alarm flashes for 30 seconds")
    @Test
    public void alarmFlashesThirtySeconds(){
        system.close();
        system.lock();
        system.tick();
        system.tick();

        system.open();

        for (int i = 0; i < 30; i++){
            system.tick();
        }

        assertTrue(system.flash());
    }

    @Tag("Alarm flash stops after 30 seconds")
    @Test
    public void alarmFlashStops(){
        system.close();
        system.lock();
        system.tick();
        system.tick();

        system.open();

        for (int i = 0; i < 31; i++){
            system.tick();
        }

        assertFalse(system.flash());
    }

}
