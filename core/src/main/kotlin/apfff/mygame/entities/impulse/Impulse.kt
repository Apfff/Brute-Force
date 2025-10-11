package apfff.mygame.entities.impulse

import apfff.mygame.entities.PhysicEntity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import ktx.math.minus
import ktx.math.times


class Impulse (
  val pos: Vector2,
  val radius: Float,
  val strength: Float,
  val falloff: (distanceFrac: Float) -> Float =  {1 - it},
  val color: Color = Color.YELLOW
): PhysicEntity {



  fun computeForceAt(pos: Vector2): Vector2 {
    val dir = pos - this.pos.cpy()
    val distance = dir.len()
    if(distance > radius){
      return Vector2()
    }
    val distanceFrac = (distance / radius).coerceIn(0f,1f)
    val magnitude = strength * falloff(distanceFrac)
    return dir.nor() * (magnitude)
  }
}