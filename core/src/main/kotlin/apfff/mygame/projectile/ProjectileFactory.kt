package apfff.mygame.projectile

import apfff.mygame.projectile.components.DragComponent
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

object ProjectileFactory {
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

  fun create(type: (Vector2) -> Projectile, pos: Vector2): Projectile {
    return type(pos)
  }
  fun createAtMousePos(type: (Vector2) -> Projectile): Projectile {
    return create(
      type,
      Vector2(
        Gdx.input.x.toFloat(),
        Gdx.graphics.height - Gdx.input.y.toFloat()
      )
    )
  }
}