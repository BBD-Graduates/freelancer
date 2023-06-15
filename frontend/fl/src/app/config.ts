export const config = {
  apiGatewayUrl: 'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8085/',
  projectService: '',
  skillApi: {
    getCategoryByCategoryId:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8082/categories?categoryId=',
    getSkills:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8082/skills',
    getSkillBySkillId:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8082/skills?skillId=',
    getCategorySkills:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8082/categories',
  },
  projectApi: {
    getProject:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8083/projects?projectId=0',
    getProjectByCategoryId:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8083/projects?categoryId=',
    getProjectBySkillId:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8083/projects?skillId=',
    getProjectByProjectId:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8083/projects?projectId=',
    insertProject:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8083/projects',
    getProjects:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8083/projects',
    getAllStatusProjects:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8083/projects/allStatusProjects',
  },
  BidService: '',
  BidApi: {
    insertBid:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8084/bids',
    updateBid:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8084/bids/updateBidStatus',
  },

  UserService: '',
  UserApi: {
    getLocation:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8081/users',
    getUser:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8081/users',
    postUser:
      'http://ec2-3-253-70-13.eu-west-1.compute.amazonaws.com:8081/users',
  },
};
