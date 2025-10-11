package apfff.mygame

import apfff.mygame.impulse.Explosion
import apfff.mygame.projectile.ProjectileFactory
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import kotlin.math.pow

class InputHandler(
  val gameEngine: GameEngine,
) {

  fun handleInputs(){
    if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
      gameEngine.addProjectile(
        ProjectileFactory.createAtMousePos(ProjectileFactory.explodeBullet)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
      gameEngine.addProjectile(
        ProjectileFactory.createAtMousePos(ProjectileFactory.heavyBullet)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
      gameEngine.addProjectile(
        ProjectileFactory.createAtMousePos(ProjectileFactory.dragBullet)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.F)){
      gameEngine.addImpulse(
        Explosion.mousePos(200f,80f)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
      gameEngine.addImpulse(
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