package softuni.adoptdontshop.Web;

/*  Input validations
1. Custom validator for Username and Email
2. Binding Models - Login, Register and Add dog forms
3. Does dog already exist check, when Dog is beeing added.
*/

/*  DELETE & EDIT security
Only admins can delete dog advertises (dog entity) from the Database.
Used @PreAuthorize("@dogServiceImpl.isAdmin(#principal.name, #id)") in Dogs controller.
It is set in the SecurityConfiguration class.
Button "Delete" is hidden for non-admin users.
Same logic is used for the Edit functionality.
 */

/*  Errors handled with
1. @ControllerAdvice global 404. This Exception is for all controllers.
2. @ExceptionHandler 500 - for Dogs and Breeds exception.
3. White label error page - handled with BasicErrorController from Spring Boot.

*/

/*  Interceptors  :

1. i118n implementation
2. Stats interceptor

*/

/*  Scheduler implementation

Used CRON expression in Admin panel.
Use case : to calculate the amount of raised donations on weekly base.
Use case : to clean the cache of findAllDogs() and findAllBreeds() ? Or use Cache PutVerdict / CachePut annotations

 */

/* Use caching of Breeds and Dogs objects
* 1. Enable Caching in Spring boot with @@EnableCaching annotation. The put @Cacheble annotation of the method you want to cache.
* 2.
*
 */

/*

User is locked() functionality ?
  */



