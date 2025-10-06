package apfff.mygame

import com.badlogic.gdx.Gdx
import kotlin.apply
import kotlin.compareTo

class GameEngine(
  val tps: Int
) {
  private val tickDt: Float = 1f/tps
  private var timeAccumulation = 0f

  private val projectiles = ArrayList<Projectile>()
  private val pendingProjectilesBuffer = ArrayList<Projectile>()
  private val pendingImpulsesBuffer = ArrayList<Impulse>()
  private var applyForcesBuffer = false //temp

  private val physicsService = PhysicsService(tickDt, projectiles)

  fun update(dt: Float){
    timeAccumulation += dt
    while (timeAccumulation >= tickDt){
      //create bodies
      addPending()
      //handle physics
      if(applyForcesBuffer){
        applyForces()
      }
      physicsService.step()
      //game state
      projectiles.removeIf {
        it.pos.x < 0 || it.pos.x > Gdx.graphics.width ||
            it.pos.y < 0 || it.pos.y > Gdx.graphics.height
      }

      timeAccumulation -= tickDt
      timeAccumulation = timeAccumulation.coerceAtLeast(0f)
    }
  }

  private fun addPending(){
    projectiles.addAll(pendingProjectilesBuffer)
    physicsService.getImpulses().addAll(pendingImpulsesBuffer)
    pendingProjectilesBuffer.clear()
    pendingImpulsesBuffer.clear()
  }

  private fun applyForces(){
    physicsService.applyForces()
    applyForcesBuffer = false
  }

  fun addPendingImpulse(impulse: Impulse) {
    pendingImpulsesBuffer.add(impulse)
  }
  fun addPendingProjectile(projectile: Projectile) {
    pendingProjectilesBuffer.add(projectile)
  }
  fun requestApplyForces(){
    applyForcesBuffer = true
  }

  fun getProjectiles(): ArrayList<Projectile> {
    return projectiles
  }
  fun getImpulses(): ArrayList<Impulse> {
    return physicsService.getImpulses()
  }

  fun getAlpha(): Float{
    return timeAccumulation / tickDt
  }

  fun reset(){
    timeAccumulation = 0f
    projectiles.clear()
    pendingProjectilesBuffer.clear()
    pendingImpulsesBuffer.clear()
    applyForcesBuffer = false
    physicsService.reset()
  }

}