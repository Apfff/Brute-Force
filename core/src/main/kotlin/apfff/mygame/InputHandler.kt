package apfff.mygame

import apfff.mygame.entities.impulse.ImpulseFactory
import apfff.mygame.entities.projectile.ProjectileFactory
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class InputHandler(
  val gameEngine: GameEngine,
) {

  fun handleInputs(){
    if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
      gameEngine.addProjectile(
        ProjectileFactory.createAtMousePos(ProjectileFactory.explodeBullet, gameEngine)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
      gameEngine.addProjectile(
        ProjectileFactory.createAtMousePos(ProjectileFactory.scaleBullet, gameEngine)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
      gameEngine.addProjectile(
        ProjectileFactory.createAtMousePos(ProjectileFactory.dragBullet, gameEngine)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.F)){
      gameEngine.addImpulse(
        ImpulseFactory.createAtMousePos(ImpulseFactory.explosion,100f,160f)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
      gameEngine.addImpulse(
        ImpulseFactory.createAtMousePos(ImpulseFactory.shockwave,100f,160f)
      )
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.R)){
      gameEngine.reset()
    }
  }
}