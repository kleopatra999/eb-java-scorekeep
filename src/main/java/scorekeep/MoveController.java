package scorekeep;

import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.lang.Long;
import java.lang.NumberFormatException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.SecureRandom;
import java.math.BigInteger;

@RestController
@RequestMapping(value="/move/{sessionId}/{gameId}")
public class MoveController {
  private SecureRandom random = new SecureRandom();
  private MoveFactory moveFactory = new MoveFactory();
  private MoveModel model = new MoveModel();
  private GameController gameController = new GameController();
  /* GET /move/SESSION/GAME */
  @RequestMapping(method=RequestMethod.GET)
  public List<Move> getMoves(@PathVariable String sessionId, @PathVariable String gameId) throws SessionNotFoundException, GameNotFoundException {
    return moveFactory.getMoves(sessionId, gameId);
  }
  /* POST /move/SESSION/GAME/USER ; move string */
  @RequestMapping(value="/{userId}", method=RequestMethod.POST)
  public Move newMove(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String userId, @RequestBody String move) throws SessionNotFoundException, GameNotFoundException, StateNotFoundException {
    return moveFactory.newMove(sessionId, gameId, userId, move);
  }
  /** GET /move/SESSION/GAME/MOVE **/
  @RequestMapping(value="/{moveId}", method=RequestMethod.GET)
  public Move getMove(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String moveId) throws SessionNotFoundException, GameNotFoundException, MoveNotFoundException {
    return moveFactory.getMove(sessionId, gameId, moveId);
  }
}
