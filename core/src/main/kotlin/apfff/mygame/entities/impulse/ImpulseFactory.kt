package apfff.mygame.entities.impulse

import apfff.mygame.entities.projectile.components.PhysicsComponent
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import kotlin.math.pow

object ImpulseFactory {
  val explosion = {pos: Vector2, radius: Float, strength: Float ->
    Impulse(
      pos = pos,
      radius = radius,
      strength = strength
    )
  }

  val shockwave = {pos: Vector2, radius: Float, strength: Float ->
    Impulse(
      pos = pos,
      radius = radius,
      strength = strength,
      falloff = {(1- it).pow(3)}
    )
  }

  fun create(type: (Vector2, Float, Float) -> Impulse, pos: Vector2, radius: Float, strength: Float): Impulse {
    return type(pos, radius, strength);
  }

  fun createAtMousePos(type: (Vector2, Float, Float) -> Impulse, radius: Float, strength: Float): Impulse {
    return create(
      type,
      pos = Vector2(
        Gdx.input.x.toFloat(),
        Gdx.graphics.height - Gdx.input.y.toFloat()
      ),
      radius = radius,
      strength = strength
    )
  }
}