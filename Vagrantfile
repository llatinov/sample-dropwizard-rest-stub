Vagrant.configure('2') do |config|

  config.vm.hostname = 'dropwizard'
  config.vm.box = 'opscode-centos-7.2'
  config.vm.box_url = 'http://opscode-vm-bento.s3.amazonaws.com/vagrant/virtualbox/opscode_centos-7.2_chef-provisionerless.box'

  config.vm.synced_folder './', '/vagrant'

  config.vm.define 'jar' do |jar|
    jar.vm.network :forwarded_port, guest: 9000, host: 9100
    jar.vm.network :forwarded_port, guest: 9001, host: 9101

    jar.vm.provider :virtualbox do |vb|
      vb.name = 'dropwizard-rest-stub-jar'
    end

    jar.vm.provision :shell do |shell|
      shell.inline = <<-SHELL
        sudo service dropwizard stop
        sudo yum -y install java
        sudo mkdir -p /var/dropwizard-rest-stub
        sudo mkdir -p /var/dropwizard-rest-stub/logs
        sudo cp /vagrant/target/sample-dropwizard-rest-stub-1.0-SNAPSHOT.jar /var/dropwizard-rest-stub/dropwizard-rest-stub.jar
        sudo cp /vagrant/config-vagrant.yml /var/dropwizard-rest-stub/config.yml
        sudo cp /vagrant/linux_service_file /etc/init.d/dropwizard
        # Replace CR+LF with LF because of Windows
        sudo sed -i -e 's/\r//g' /etc/init.d/dropwizard
        sudo service dropwizard start
      SHELL
    end
  end

  config.vm.define 'docker' do |docker|
    docker.vm.network :forwarded_port, guest: 9000, host: 9000
    docker.vm.network :forwarded_port, guest: 9001, host: 9001

    docker.vm.provider :virtualbox do |vb|
      vb.name = 'dropwizard-rest-stub-docker'
      vb.customize ['modifyvm', :id, '--memory', '768', '--cpus', '2']
    end
  
    docker.vm.provision :shell do |shell|
      shell.inline = <<-SHELL
        sudo yum -y install epel-release
        sudo yum -y install python-pip
        sudo pip install --upgrade pip
        sudo pip install six==1.4
        sudo pip install docker-py
      SHELL
    end
  
    docker.vm.provision :docker do |docker|
      docker.build_image '/vagrant/.', args: '-t dropwizard-rest-stub'
      docker.run 'dropwizard-rest-stub', args: '-it -p 9000:9000 -p 9001:9001 -e ENV_VARIABLE_VERSION=1.1.1'
    end
  end
  
end