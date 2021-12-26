package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserBean {
    @PersistenceContext
    EntityManager em;

    public User authenticate(final String id, final String password) throws Exception {
        User user = em.find(User.class, id);
        if (user != null &&
                user.getPassword().equals(User.hashPassword(password))) {
            return user;
        }
        throw new Exception("Failed logging in with username '" + id + "': unknown username or wrong password");
    }
}
