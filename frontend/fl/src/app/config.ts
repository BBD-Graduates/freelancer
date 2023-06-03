export const config = {
  apiGatewayUrl: 'http://localhost:8085/',
  projectService: '',
  skillApi: {
    getCategoryByCategoryId: 'http://localhost:8082/categories?categoryId=',
    getSkills: 'http://localhost:8082/skills',
    getSkillBySkillId: 'http://localhost:8082/skills?skillId=',
    getCategorySkills: 'http://localhost:8082/categories',
  },
  projectApi: {
    getProject: 'http://localhost:8083/projects?projectId=0',
    getProjectByCategoryId: 'http://localhost:8083/projects?categoryId=',
    getProjectBySkillId: 'http://localhost:8083/projects?skillId=',
    getProjectByProjectId: 'http://localhost:8083/projects?projectId=',
    insertProject: 'http://localhost:8083/projects',
  },
  BidService: '',
  BidApi: {
    insertBid: 'http://localhost:8084/bids',
  },

  UserService: '',
  UserApi: {
    getLocation: 'http://localhost:8081/users',
    getUser: 'http://localhost:8081/users',
    postUser: 'http://localhost:8081/users',
  },
};
