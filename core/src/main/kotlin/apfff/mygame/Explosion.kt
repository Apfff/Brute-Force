package apfff.mygame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import ktx.math.minus
import ktx.math.times


class Explosion (
  val pos: Vector2,
  val strength: Float,
  val radius: Float,
  val falloff: (distanceFrac: Float) -> Float =  {1 - it},
  val color: Color = Color.YELLOW
): Impulse {

  override fun computeForceAt(pos: Vector2): Vector2 {
    val dir = pos - this.pos.cpy()
    val distance = dir.len()
    if(distance > radius){
      return Vector2()
    }
    val distanceFrac = distance / radius
    val magnitude = strength * falloff(distanceFrac)
    return dir.nor() * (magnitude)
  }

  companion object {
    fun mousePos(strength: Float, radius: Float, falloff: (distanceFrac: Float) -> Float =  {1 - it}, color: Color = Color.YELLOW) =
      Explosion(
        Vector2(
          Gdx.input.x.toFloat(),
          Gdx.graphics.height - Gdx.input.y.toFloat()
        ),
        strength,
        radius,
        falloff,
        color
      )
  }
}