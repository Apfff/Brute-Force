package apfff.mygame.projectile

import apfff.mygame.projectile.components.DragComponent
import apfff.mygame.projectile.components.PhysicsComponent
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

object Projectiles {
  val basicBullet = { pos : Vector2 ->
    Projectile(
      pos = pos,
      radius = 10f
    )
  }

  val heavyBullet = { pos : Vector2 ->
    Projectile(
      pos = pos,
      radius = 10f,
      mass = 2f,
      color = Color.PURPLE
    )
  }

  val dragBullet = { pos : Vector2 ->
    Projectile(
      pos = pos,
      radius = 10f,
      color = Color.BLUE,
      physicsComponents = arrayListOf(DragComponent(0.4f))
    )
  }

}