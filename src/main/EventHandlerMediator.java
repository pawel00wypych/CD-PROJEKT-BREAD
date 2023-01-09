package main;

public interface EventHandlerMediator {
    void registerGamePanel(GamePanel gp);
    void registerEventRect(EventRect[][][] eventRect);
    void checkEvent();
}
