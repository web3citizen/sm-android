�}lA  Q  ���=�#Y�e��S��u6��*�LIX7�S���ȯ�L�J��V�`�D��{b n���瀃N�,E]�i�4��s�Χ�S��h!���8�;�=���D�!Q���_([ ��:�K����j$o��'B�ytTm�_%͟��c�����30(z���o���y�YA��@���uJ�ʽ�A�XZ☹1��_Xfj������o����u`��P��w�d��G�s�c��M�t�<uJ����?��t���[t�!g@Ͳ��,?��C��B"�Iٵɔ`�;���y܍�,P޺�}C%O]�Y��0��
5u	񘾋F@m�
�Ax�\�K�5f����2K�@\��OD�����ÂQ؍��C�����Ϋ��5�;�h"�3�AjT{*�r��¬FPڳs���\�ʴ`�V/,�2_I��y���� �mX����������>�i�f@�w��DZ���g���4�� B�%�OY;i4O�:��/�/�lD*|`�����s� [|�]���F��5�k�O����j���d*��nQ= [�t�{4"���z�� <L͠q�
�|�u����L�I�ZU �b����y��D�e�����npC�iW-����H7��6�Sz�a%[��{���O҃Y9��*}�t��k�߇�,y��y�l��9~zM4�Ϯ�������B��?���=�)#�U�KD!d�7-�j�΋�c��f91���YjCM.b�O�r �������l]7&Ƣ�r5ɡ�.I���7M�0[+=8+��9I�{%zp�3{����oXQ�L�IlQHB!4�>��5�N���L���1�C7,�NV�'Y,`� 'Z�l����,/�罉g�AqX9~`\S�R�6�{��B]R��q�Z;8:�X�`�K�E��0�Y+*GL!/��!�}������:��&⾒ot���:�Pe���=>G	�<�y��[�dd��cO���O�](r]��pRx������f@�~#/0ace View extends MvpView {

        void showRepoList(List<Repo> repos);

        void showToast();

    }

    interface Presenter {

        void loadRepos(Tag tag);

        void update(Tag tag);

    }
}
