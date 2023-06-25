export const config = {
  apiGatewayUrl: 'https://d27tejefvay96.cloudfront.net:8085/',
  projectService: '',
  skillApi: {
    getCategoryByCategoryId:
      'https://d27tejefvay96.cloudfront.net/fls/categories?categoryId=',
    getSkills: 'https://d27tejefvay96.cloudfront.net/fls/skills',
    getSkillBySkillId:
      'https://d27tejefvay96.cloudfront.net/fls/skills?skillId=',
    getCategorySkills: 'https://d27tejefvay96.cloudfront.net/fls/categories',
    userSkills:'https://d27tejefvay96.cloudfront.net/fls/user-skills',
  },
  projectApi: {
    getProject: 'https://d27tejefvay96.cloudfront.net/flp/projects?projectId=0',
    getProjectByCategoryId:
      'https://d27tejefvay96.cloudfront.net/flp/projects?categoryId=',
    getProjectBySkillId:
      'https://d27tejefvay96.cloudfront.net/flp/projects?skillId=',
    getProjectByProjectId:
      'https://d27tejefvay96.cloudfront.net/flp/projects?projectId=',
    insertProject: 'https://d27tejefvay96.cloudfront.ne/flp/projects',
    getProjects: 'https://d27tejefvay96.cloudfront.net/flp/projects',
    getAllStatusProjects:
      'https://d27tejefvay96.cloudfront.net/flp/projects/allStatusProjects',
  },
  BidService: '',
  BidApi: {
    insertBid: 'https://d27tejefvay96.cloudfront.net/flb/bids',
    updateBid: 'https://d27tejefvay96.cloudfront.net/flb/bids/updateBidStatus',
  },

  UserService: '',
  UserApi: {
    getLocation: 'https://d27tejefvay96.cloudfront.net/flu/users',
    getUser: 'https://d27tejefvay96.cloudfront.net/flu/users',
    postUser: 'https://d27tejefvay96.cloudfront.net/flu/users',
  },
};
