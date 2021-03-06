package ubc.cosc322;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import ygraph.ai.smartfox.games.BaseGameGUI;
import ygraph.ai.smartfox.games.GameClient;
import ygraph.ai.smartfox.games.GamePlayer;

public abstract class LocalPlayer extends GamePlayer {
  private String username;
  private String password;

  protected AmazonsLocalBoard board = new AmazonsLocalBoard();
  protected AmazonsActionFactory actionFactory = new AmazonsActionFactory();

  private GameClient gameClient = null;
  private BaseGameGUI gameGUI = null;

  public LocalPlayer(String username, String password) {
    this.username = username;
    this.password = password;
    gameGUI = new BaseGameGUI(this);
  }

  // ===== LocalPlayer API ===== //

  /** Computes the next move and sends it to the server. */
  protected abstract void move();

  /** Returns the list of actions that can be taken from the current state. */
  protected ArrayList<AmazonsAction> getAvailableActions() {
    return actionFactory.getActions(board);
  }

  /** Sends a move to the server and updates the local state accordingly. */
  protected void sendMove(List<Integer> queenCurrent, List<Integer> queenTarget, List<Integer> arrowTarget) {
    board.updateState(queenCurrent, queenTarget, arrowTarget);
    board.printState();
    gameGUI.updateGameState(new ArrayList<>(queenCurrent), new ArrayList<>(queenTarget), new ArrayList<>(arrowTarget));
    gameClient.sendMoveMessage(new ArrayList<>(queenCurrent), new ArrayList<>(queenTarget), new ArrayList<>(arrowTarget));
  }

  /** Called when the player receives a move message from the server. */
  private void onMoveReceived() {
    move();
  }

  /** Called when the game starts if this player is black. */
  private void playFirstMove() {
    move();
  }

  // ===== GamePlayer Overrides ===== //

  @Override
  public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {
    switch (messageType) {
      case "cosc322.game-state.board":
        ArrayList<Integer> gameState = getMessageByTag(msgDetails, "game-state");

        board.setState(gameState);
        board.printState();
        gameGUI.setGameState(gameState);

        break;

      case "cosc322.game-action.move":
        ArrayList<Integer> queenCurrent = getMessageByTag(msgDetails, "queen-position-current");
        ArrayList<Integer> queenTarget = getMessageByTag(msgDetails, "queen-position-next");
        ArrayList<Integer> arrowTarget = getMessageByTag(msgDetails, "arrow-position");

        board.updateState(queenCurrent, queenTarget, arrowTarget);
        board.printState();
        gameGUI.updateGameState(queenCurrent, queenTarget, arrowTarget);

        onMoveReceived();

        break;

      case "cosc322.game-action.start":
        String whitePlayer = getMessageByTag(msgDetails, "player-white");

        board.localPlayer = whitePlayer.equals(username) ? 1 : 2;
        System.out.println("***** PLAYER INFO: " + username + " " + board.localPlayer + " *****");

        if (board.localPlayer == 2)
          playFirstMove();
        break;
    }

    return true;
  }

  @Override
  public void onLogin() {
    if (gameGUI != null) {
      gameGUI.setRoomInformation(gameClient.getRoomList());
    }
  }

  @Override
  public String userName() {
    return username;
  }

  @Override
  public GameClient getGameClient() {
    return gameClient;
  }

  @Override
  public BaseGameGUI getGameGUI() {
    return gameGUI;
  }

  @Override
  public void connect() {
    gameClient = new GameClient(username, password, this);
  }

  // ===== Helper Methods ===== //

  @SuppressWarnings("unchecked") // Yes, Java gives no proper way to handle this warning. You just have to suppress it.
  private <T extends Object> T getMessageByTag(Map<String, Object> messages, String tag) {
    return (T) messages.get(tag);
  }
}