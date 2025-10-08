package apfff.mygame

import apfff.mygame.impulse.Explosion
import apfff.mygame.projectile.Projectile
import apfff.mygame.projectile.Projectiles
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import kotlin.math.pow

class InputHandler(
  val gameEngine: GameEngine,
) {

  fun handleInputs(){
    if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
      gameEngine.addPendingProjectile(
        Projectiles.basicBullet(Vector2(Gdx.input.x.toFloat(), Gdx.graphics.height - Gdx.input.y.toFloat())),
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
      gameEngine.addPendingProjectile(
        Projectiles.heavyBullet(Vector2(Gdx.input.x.toFloat(), Gdx.graphics.height - Gdx.input.y.toFloat())),
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
      gameEngine.addPendingProjectile(
        Projectiles.dragBullet(Vector2(Gdx.input.x.toFloat(), Gdx.graphics.height - Gdx.input.y.toFloat())),
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