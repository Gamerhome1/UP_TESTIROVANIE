import models.requestModels.JobRequest;
import org.testng.annotations.Test;

public class ReqresApiTests extends BaseTest{
    @Test(testName = "Список пользователей")
    public static void getUsersList() {
        REQRES_API_STEPS.getUsersList();
    }

    @Test(testName = "Пользователь по айди")
    public void getUserById() {
        REQRES_API_STEPS.getUserById(2);
    }

    @Test(testName = "Несуществующий пользователь")
    public static void getNotExistUser() {
        REQRES_API_STEPS.getNoExUser(23);
    }

    @Test(testName = "Список ресурсов")
    public static void getListResource() {
        REQRES_API_STEPS.getListResource();
    }

    @Test(testName = "Получение ресурса")
    public static void getSingleResource() {
        REQRES_API_STEPS.getSingleResource();
    }

    @Test(testName = "Несуществующий ресурс")
    public static void getNotExistResource() {
        REQRES_API_STEPS.getNotExistResource();
    }

    @Test(testName = "Создание пользователя")
    public void createUser() {
        REQRES_API_STEPS.createUser("morpheus", "leader");
    }

    @Test(testName = "Обновление пользователя")
    public static void putUpdateUser() {
        REQRES_API_STEPS.updateUser("morpheus", "zion resident");
    }

    @Test(testName = "Удаление пользователя")
    public static void deleteUser() {
        REQRES_API_STEPS.deleteUser();
    }

    @Test(testName = "регистрация пользователя (+)")
    public static void registerSuccessful() {
        REQRES_API_STEPS.checkSuccessReg("eve.holt@reqres.in", "pistol");
    }

    @Test(testName = "регистрация пользователя (-)")
    public static void registerUnSuccessful() {
        REQRES_API_STEPS.checkRegUnSuccess("eve.holt@reqres.in");
    }

    @Test(testName = "авторизация пользователя (+)")
    public static void loginSuccessful() {
        REQRES_API_STEPS.checkLogSuccess("eve.holt@reqres.in", "cityslicka");
    }

    @Test(testName = "авторизация пользователя (-)")
    public static void loginUnSuccessful() {
        REQRES_API_STEPS.checkLogUnSuccess("eve.holt@reqres.in");
    }

    @Test(testName = "Отложенный запрос")
    public static void getDelayedResp() {
        REQRES_API_STEPS.getDelayUserList();
    }
}
