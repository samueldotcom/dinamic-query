import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

public class DynamicQueryExample {

    public void getUsersByFirstName(String firstName) {
        try {
            // Cria uma Dynamic Query para a entidade User
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);

            // Adiciona restrições à consulta
            dynamicQuery.add(PropertyFactoryUtil.forName("firstName").eq(firstName));

            // Executa a consulta
            List<User> users = UserLocalServiceUtil.dynamicQuery(dynamicQuery);

            // Exibe os resultados
            for (User user : users) {
                System.out.println("ID do usuário: " + user.getUserId());
                System.out.println("Nome do usuário: " + user.getFullName());
            }
        } catch (SystemException e) {
            _log.error("Erro ao executar a consulta dinâmica: " + e.getMessage());
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(DynamicQueryExample.class);

    public static void main(String[] args) {
        DynamicQueryExample example = new DynamicQueryExample();
        example.getUsersByFirstName("John");
    }
}
