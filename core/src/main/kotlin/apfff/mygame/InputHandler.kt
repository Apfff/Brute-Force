package apfff.mygame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import kotlin.math.pow

class InputHandler(
  val gameEngine: GameEngine,
) {

  fun handleInputs(){
    if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
      gameEngine.addPendingProjectile(
        Bullet.mousePos(10f)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
      gameEngine.addPendingProjectile(
        Bullet.mousePos(10f, rotation = 90f, color = Color.ORANGE)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
      gameEngine.addPendingProjectile(
        Bullet.mousePos(10f, drag = 0.4f, color = Color.BLUE)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.F)){
      gameEngine.addPendingImpulse(
        Explosion.mousePos(200f,80f)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
      gameEngine.addPendingImpulse(
        Explosion.mousePos(400f,140f,
          {(1- it).pow(3)},
          Color.CHARTREUSE
        )
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      gameEngine.requestApplyForces()
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.R)){
      gameEngine.reset()
    }
  }
}