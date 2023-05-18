export const config = {
    apiGatewayUrl: 'http://localhost:8085/',
    projectService: '',
    skillApi: {
        getCategorySkills: 'http://localhost:8082/categories'
    },
    projectApi: {
        getProject: 'http://localhost:8083/projects',
        getProjectByProjectId: 'http://localhost:8083/projects?projectId='
    },

}