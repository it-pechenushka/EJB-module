package ru.ilyai.lab2.service;

import org.json.JSONObject;
import ru.ilyai.lab2.exception.DataNotFoundException;
import ru.ilyai.lab2.exception.InvalidParamsException;
import ru.ilyai.lab2.exception.UnavailableServerException;
import ru.ilyai.lab2.helper.HelperFactory;
import lombok.SneakyThrows;
import ru.ilyai.lab2.model.StudyGroupDto;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Remote(ExpelService.class)
public class ExpelServiceImpl implements ExpelService{
    private final Client client;

    public ExpelServiceImpl() {
        this.client = HelperFactory.getClient();
    }

    @SneakyThrows
    public void expelStudents(long groupId) {
        List<JSONObject> services = HelperFactory.getAvailableServices();
        if (services.isEmpty())
            throw new UnavailableServerException("Service unavailable now! Try again later");

        Response response = client.target(HelperFactory.getServiceUrl(services.get(0).getString("Address"), services.get(0).getInt("Port")) + "/base/groups/" +  groupId)
                .request(MediaType.TEXT_XML)
                .get();

        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()){
            throw new DataNotFoundException("Study group not found!");
        }
        if (response.getStatus() > 300) throw new InvalidParamsException("Storage request failed with status: " + response.getStatus());

        StudyGroupDto studyGroup = response.readEntity(StudyGroupDto.class);
        StudyGroupDto studyGroupToUpdate = new StudyGroupDto();
        studyGroupToUpdate.setShouldBeExpelled(1);

        if (studyGroup.getStudentsCount() > 1){
            studyGroupToUpdate.setExpelledStudents(studyGroup.getExpelledStudents() + studyGroup.getStudentsCount() - 1);
            studyGroupToUpdate.setStudentsCount(1L);
        }

        response = client.target(HelperFactory.getServiceUrl(services.get(0).getString("Address"), services.get(0).getInt("Port")) + "/base/groups/" + groupId)
                .request(MediaType.TEXT_XML)
                .put(Entity.entity(studyGroupToUpdate, MediaType.TEXT_XML));

        if (response.getStatus() > 300) throw new InvalidParamsException("Storage request failed with status: " + response.getStatus());

    }
}
