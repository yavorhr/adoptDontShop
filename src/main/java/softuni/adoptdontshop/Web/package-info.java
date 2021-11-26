package softuni.adoptdontshop.Web;

/* Input validations
* 1. Custom validator for Username and Email
* 2. Binding Models - Login, Register and Add dog forms
* 3. Does dog already exist check, when Dog is beeing added.
* */

/* DELETE & EDIT security
* Only admins can delete dog advertises (dog entity) from the Database.
Used @PreAuthorize("@dogServiceImpl.isAdmin(#principal.name, #id)") in Dogs controller.
It is set in the SecurityConfiguration class.
* Button "Delete" is hidden for non-admin users.
* Same logic is used for the Edit functionality.
 */

/* Errors handled with
//TODO : да помисля коя грешка да ми е в @ControllerAdvice. И коя грешка да е персонализирана за определен контролер.
1. @ControllerAdvice global 404. This Exception is for all controllers.
2. @ExceptionHandler 500 - for Dogs and Breeds exception. Customized only for Dogs controller
3. White label error page - handled with BasicErrorController from Spring Boot.
* 403 forbidden - when non-admin user tries to delete dog entity or to edit dog entity
* 404 not found - when no mapping is implemented.
* 500 - no explicit mapping
* Custom exception - no dog found
 */

/*i118n implementation*/

