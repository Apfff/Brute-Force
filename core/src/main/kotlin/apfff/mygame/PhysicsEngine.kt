package apfff.mygame

import apfff.mygame.entities.impulse.Impulse
import apfff.mygame.entities.projectile.Projectile
import com.badlogic.gdx.math.Vector2
import ktx.math.plusAssign

class PhysicsEngine(
  val tickDt: Float,
  val projectiles: ArrayList<Projectile>
): Service {

  val impulseBuffer = ArrayList<Impulse>()


  fun step(){
    for (projectile in projectiles) {
      projectile.step(tickDt)
    }
  }

  fun addImpulse(vararg impulse: Impulse) {
    impulseBuffer.addAll(impulse)
  }

  fun resetImpulses(){
    impulseBuffer.clear()
  }

  fun getImpulses(): ArrayList<Impulse> {
    return impulseBuffer
  }

  fun computeForcesAt(pos: Vector2): Vector2{
    val result = Vector2()
    for (impulse in impulseBuffer){
      result += impulse.computeForceAt(pos)
    }
    return result
  }

  fun applyForces(){
    for (projectile in projectiles) {
      projectile.applyForce(computeForcesAt(projectile.pos))
    }
    resetImpulses()
  }

  override fun reset(){
    resetImpulses()
  }
}