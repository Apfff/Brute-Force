package apfff.mygame.entities.projectile

import apfff.mygame.GameEngine
import apfff.mygame.entities.impulse.ImpulseFactory
import apfff.mygame.entities.projectile.components.DragComponent
import apfff.mygame.entities.projectile.components.GrowComponent
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

object ProjectileFactory {
  val basicBullet = { pos : Vector2, gameEngine: GameEngine ->
    Projectile(
      pos = pos,
      radius = 10f
    )
  }

  val scaleBullet = { pos : Vector2, gameEngine: GameEngine ->
    Projectile(
      pos = pos,
      radius = 10f,
      color = Color.PURPLE,
      behaviorComponents = arrayListOf(GrowComponent(1f, 1.2f))
    )
  }

  val dragBullet = { pos : Vector2, gameEngine: GameEngine ->
    Projectile(
      pos = pos,
      radius = 10f,
      color = Color.BLUE,
      physicsComponents = arrayListOf(DragComponent(0.4f))
    )
  }

  val explodeBullet = { pos: Vector2, gameEngine: GameEngine ->
    Projectile(
      pos = pos,
      radius = 15f,
      ttl = 2f,
      color = Color.ROYAL
    ).apply {
      on(TriggerEvent.EXPIRE) { p ->
        gameEngine.addImpulse(
          ImpulseFactory.create(ImpulseFactory.shockwave, p.pos, 100f, 400f)
        )
      }
    }
  }

  fun create(type: (Vector2, GameEngine) -> Projectile, pos: Vector2, gameEngine: GameEngine): Projectile {
    return type(pos, gameEngine)
  }
  fun createAtMousePos(type: (Vector2, GameEngine) -> Projectile, gameEngine: GameEngine): Projectile {
    return create(
      type,
      Vector2(
        Gdx.input.x.toFloat(),
        Gdx.graphics.height - Gdx.input.y.toFloat()
      ),
      gameEngine
    )
  }
}