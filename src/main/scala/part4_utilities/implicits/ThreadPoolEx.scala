package part4_utilities

import ThreadPoolEx.ThreadPool
import ThreadPoolProvider.threadPool

import java.awt.desktop.UserSessionEvent
import java.util.concurrent.{ExecutorService, Executors}

object ThreadPoolEx {
  
  class ThreadPool {
    private final val threadPool: ExecutorService = Executors.newFixedThreadPool(10)
    def submit[A <: Runnable](task: A): Unit = threadPool.submit(task)
  }

  trait EmailService {
    def sendEmail(msg: String): Unit = ???
  }
  
  trait UserRepository

  
  class UserSubscriptionService(emailService: EmailService) {
    def notify(message: String): Unit = emailService.sendEmail(message)
  }

  def main(args: Array[String]): Unit = {
    
    
  }
  
}

object ThreadPoolProvider {
  given threadPool: ThreadPool = ThreadPool()
}
